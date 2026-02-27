using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace CoffeeAPI.Models
{
    public class Order
    {
        public int Id { get; set; }

        public int UserId { get; set; }

        public decimal TotalPrice { get; set; }

        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;

        public List<OrderItem> Items { get; set; } = new();
    }
}