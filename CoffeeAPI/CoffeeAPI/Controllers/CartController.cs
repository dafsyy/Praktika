using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using CoffeeAPI.Data;
using CoffeeAPI.Models;

namespace CoffeeAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class CartController : ControllerBase
    {
        private readonly CoffeeDbContext _context;

        public CartController(CoffeeDbContext context)
        {
            _context = context;
        }

        // GET: api/cart/1
        [HttpGet("{userId}")]
        public async Task<ActionResult> GetCart(int userId)
        {
            var cart = await _context.CartItems
                .Where(c => c.UserId == userId)
                .Include(c => c.Coffee)
                .ToListAsync();

            return Ok(cart);
        }

        // POST: api/cart/add
        [HttpPost("add")]
        public async Task<ActionResult> AddToCart(CartItem item)
        {
            var existing = await _context.CartItems
                .FirstOrDefaultAsync(c =>
                    c.UserId == item.UserId &&
                    c.CoffeeId == item.CoffeeId);

            if (existing != null)
            {
                existing.Quantity += item.Quantity;
            }
            else
            {
                _context.CartItems.Add(item);
            }

            await _context.SaveChangesAsync();
            return Ok();
        }

        // DELETE: api/cart/remove/5
        [HttpDelete("remove/{id}")]
        public async Task<ActionResult> Remove(int id)
        {
            var item = await _context.CartItems.FindAsync(id);
            if (item == null)
                return NotFound();

            _context.CartItems.Remove(item);
            await _context.SaveChangesAsync();

            return Ok();
        }

        // DELETE: api/cart/clear/1
        [HttpDelete("clear/{userId}")]
        public async Task<ActionResult> Clear(int userId)
        {
            var items = _context.CartItems.Where(c => c.UserId == userId);
            _context.CartItems.RemoveRange(items);
            await _context.SaveChangesAsync();

            return Ok();
        }
    }
}