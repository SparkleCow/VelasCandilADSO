//NOTA: el backend extrae el userId directamente del JWT via @AuthenticationPrincipal.
//El frontend NO necesita enviar el userId en ningún request.
export interface CartItemResponse {
  id: number;
  candleId: number;
  candleName: string;
  price: number; 
  quantity: number;
  subtotal: number; 
}

export interface ShoppingCartResponse {
  id: number;
  subTotal: number; 
  items: CartItemResponse[];
}