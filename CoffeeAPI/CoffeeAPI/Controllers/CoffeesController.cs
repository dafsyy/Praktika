using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using CoffeeAPI.Data;
using CoffeeAPI.Models;
using System.Linq;

namespace CoffeeAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class CoffeesController : ControllerBase
    {
        private readonly CoffeeDbContext _context;

        public CoffeesController(CoffeeDbContext context)
        {
            _context = context;
        }

        // GET: api/coffees
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Coffee>>> GetCoffees()
        {
            return await _context.Coffees.ToListAsync();
        }

        // GET: api/coffees/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Coffee>> GetCoffee(int id)
        {
            var coffee = await _context.Coffees.FindAsync(id);

            if (coffee == null)
                return NotFound();

            return coffee;
        }

        // POST: api/coffees
        [HttpPost]
        public async Task<ActionResult<Coffee>> CreateCoffee(Coffee coffee)
        {
            _context.Coffees.Add(coffee);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetCoffee), new { id = coffee.Id }, coffee);
        }

        // PUT: api/coffees/5
        [HttpPut("{id}")]
        public async Task<IActionResult> UpdateCoffee(int id, Coffee coffee)
        {
            if (id != coffee.Id)
                return BadRequest();

            _context.Entry(coffee).State = EntityState.Modified;
            await _context.SaveChangesAsync();

            return NoContent();
        }

        // DELETE: api/coffees/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCoffee(int id)
        {
            var coffee = await _context.Coffees.FindAsync(id);
            if (coffee == null)
                return NotFound();

            _context.Coffees.Remove(coffee);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        // GET: api/coffees/category/Milk
        [HttpGet("category/{category}")]
        public async Task<ActionResult<IEnumerable<Coffee>>> GetByCategory(string category)
        {
            var coffees = await _context.Coffees
                .Where(c => c.Category.ToLower() == category.ToLower())
                .ToListAsync();

            return coffees;
        }
    }
}