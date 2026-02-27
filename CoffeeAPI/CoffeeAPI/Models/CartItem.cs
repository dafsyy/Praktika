using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CoffeeAPI.Models
{
    public class CartItem
    {
        public int Id { get; set; }

        [Required]
        public int UserId { get; set; }

        [Required]
        public int CoffeeId { get; set; }

        public int Quantity { get; set; } = 1;

        [ForeignKey("UserId")]
        public User? User { get; set; }

        [ForeignKey("CoffeeId")]
        public Coffee? Coffee { get; set; }
    }
}