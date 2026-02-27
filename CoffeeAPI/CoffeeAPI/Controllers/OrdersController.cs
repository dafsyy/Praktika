using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using CoffeeAPI.Data;
using CoffeeAPI.Models;

namespace CoffeeAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class OrdersController : ControllerBase
    {
        private readonly CoffeeDbContext _context;

        public OrdersController(CoffeeDbContext context)
        {
            _context = context;
        }

        // POST: api/orders
        [HttpPost]
        public async Task<ActionResult> CreateOrder(Order order)
        {
            order.CreatedAt = DateTime.UtcNow;

            _context.Orders.Add(order);
            await _context.SaveChangesAsync();

            return Ok(order.Id);
        }

        // GET: api/orders/user/1
        [HttpGet("user/{userId}")]
        public async Task<ActionResult> GetUserOrders(int userId)
        {
            var orders = await _context.Orders
                .Where(o => o.UserId == userId)
                .Include(o => o.Items)
                .ThenInclude(i => i.Coffee)
                .ToListAsync();

            return Ok(orders);
        }
    }
}