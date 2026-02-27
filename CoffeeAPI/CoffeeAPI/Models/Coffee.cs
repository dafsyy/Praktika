using System.ComponentModel.DataAnnotations;

namespace CoffeeAPI.Models
{
    public class Coffee
    {
        public int Id { get; set; }

        [Required]
        [MaxLength(100)]
        public string Name { get; set; } = string.Empty;

        [MaxLength(500)]
        public string Description { get; set; } = string.Empty;

        public decimal Price { get; set; }

        public string ImageUrl { get; set; } = string.Empty;

        [MaxLength(50)]
        public string Category { get; set; } = string.Empty;
    }
}