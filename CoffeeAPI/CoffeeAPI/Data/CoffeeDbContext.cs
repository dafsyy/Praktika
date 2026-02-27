using Microsoft.EntityFrameworkCore;
using CoffeeAPI.Models;


namespace CoffeeAPI.Data
{
    public class CoffeeDbContext : DbContext
    {
        public CoffeeDbContext(DbContextOptions<CoffeeDbContext> options)
            : base(options) { }

        public DbSet<Coffee> Coffees { get; set; }
        public DbSet<User> Users { get; set; }

        public DbSet<CartItem> CartItems { get; set; }

        public DbSet<Order> Orders { get; set; }
        public DbSet<OrderItem> OrderItems { get; set; }

        public DbSet<Product> Products { get; set; }
        public DbSet<Category> Categories { get; set; }
    }
}