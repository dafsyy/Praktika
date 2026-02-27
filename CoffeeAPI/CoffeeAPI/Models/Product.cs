using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CoffeeAPI.Models
{
    public class Product
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        public string Description { get; set; }

        [Column(TypeName = "decimal(10,2)")]
        public decimal Price { get; set; }

        public string ImageUrl { get; set; }

        // FK
        public int CategoryId { get; set; }

        // Navigation
        public Category Category { get; set; }

        public ICollection<OrderItem> OrderItems { get; set; }
    }
}