using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using CoffeeAPI.Data;
using CoffeeAPI.Models;

namespace CoffeeAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AuthController : ControllerBase
    {
        private readonly CoffeeDbContext _context;

        public AuthController(CoffeeDbContext context)
        {
            _context = context;
        }

        // POST: api/auth/register
        [HttpPost("register")]
        public async Task<ActionResult<User>> Register(User user)
        {
            var exists = await _context.Users
                .AnyAsync(u => u.Email == user.Email);

            if (exists)
                return BadRequest("Email already exists");

            _context.Users.Add(user);
            await _context.SaveChangesAsync();

            return Ok(user);
        }

        // POST: api/auth/login
        [HttpPost("login")]
        public async Task<ActionResult<User>> Login(User login)
        {
            var user = await _context.Users
                .FirstOrDefaultAsync(u =>
                    u.Email == login.Email &&
                    u.Password == login.Password);

            if (user == null)
                return Unauthorized("Invalid email or password");

            return Ok(user);
        }
    }
}