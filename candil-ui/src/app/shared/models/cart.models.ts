//NOTA: el backend extrae el userId directamente del JWT via @AuthenticationPrincipal.
//El frontend NO necesita enviar el userId en ningún request.
export interface CartItemResponseDto {
  id: number;
  candleId: number;
  candleName: string;
  price: number;
  quantity: number;
  subtotal: number;
}

export interface ShoppingCartResponseDto {
  id: number;
  subTotal: number;
  items: CartItemResponseDto[];
}

// Aliases for backward compatibility.
export type CartItemResponse = CartItemResponseDto;
export type ShoppingCartResponse = ShoppingCartResponseDto;