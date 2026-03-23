export type CategoryEnum =
  | 'AROMATIC' | 'CLASSIC' | 'DECORATIVE'
  | 'RELIGIOUS' | 'ROMANTIC' | 'OIL_LAMP';

export type MaterialEnum =
  | 'SOY_WAX' | 'BEESWAX' | 'PARAFFIN_WAX'
  | 'COCONUT_WAX' | 'PALM_WAX' | 'GEL_WAX';

export type FeatureEnum =
  | 'SCENTED' | 'ORGANIC' | 'HANDMADE' | 'VEGAN'
  | 'ECO_FRIENDLY' | 'LONG_LASTING' | 'CANNABIS_INFUSED'
  | 'ESSENTIAL_OIL_BASED' | 'RELAXATION_EFFECT'
  | 'MEDITATION_SUPPORT' | 'THERAPEUTIC_USE';

export const CATEGORIES: CategoryEnum[] = [
  'AROMATIC', 'CLASSIC', 'DECORATIVE', 'RELIGIOUS', 'ROMANTIC', 'OIL_LAMP'
];
export const MATERIALS: MaterialEnum[] = [
  'SOY_WAX', 'BEESWAX', 'PARAFFIN_WAX', 'COCONUT_WAX', 'PALM_WAX', 'GEL_WAX'
];
export const FEATURES: FeatureEnum[] = [
  'SCENTED', 'ORGANIC', 'HANDMADE', 'VEGAN', 'ECO_FRIENDLY', 'LONG_LASTING',
  'CANNABIS_INFUSED', 'ESSENTIAL_OIL_BASED', 'RELAXATION_EFFECT',
  'MEDITATION_SUPPORT', 'THERAPEUTIC_USE'
];

export interface IngredientRequest {
  name: string;
  amount: number;
}

export interface IngredientResponse {
  name: string;
  amount: number;
  price: number;
}

export interface CandleRequest {
  name: string;
  description: string;
  principalImage: string;
  stock: number;
  materialEnums: MaterialEnum[];
  featureEnums: FeatureEnum[];
  categories: CategoryEnum[];
  images: string[];
  ingredients: IngredientRequest[];
}

// El update no incluye ingredients (el backend no lo acepta en el PUT) Si quieres modificar ingredientes de una vela existente, el backend no lo expone en este endpoint, debo consultar.
export interface CandleUpdateRequest {
  name: string;
  description: string;
  principalImage: string;
  stock: number;
  materialEnums: MaterialEnum[];
  featureEnums: FeatureEnum[];
  categories: CategoryEnum[];
  images: string[];
}

export interface CandleResponse {
  id: number;
  name: string;
  description: string;
  principalImage: string;
  stock: number;
  price: number;
  materialEnums: MaterialEnum[];
  featureEnums: FeatureEnum[];
  categories: CategoryEnum[];
  images: string[];
  ingredients: IngredientResponse[];
}

export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
}