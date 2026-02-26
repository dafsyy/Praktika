using Microsoft.EntityFrameworkCore;
using CoffeeAPI.Models;


namespace CoffeeAPI.Data
{
    public class CoffeeDbContext : DbContext
    {
        public CoffeeDbContext(DbContextOptions<CoffeeDbContext> options)
            : base(options) { }

        public DbSet<Product> Products { get; set; }
        public DbSet<Order> Orders { get; set; }
        public DbSet<OrderItem> OrderItems { get; set; }
        public DbSet<Coffee> Coffees { get; set; }
    }
}