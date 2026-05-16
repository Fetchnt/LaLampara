export type TipoRevista = 'NOTICIA' | 'HOROSCOPO';

export interface Articulo {
  id: number;
  nombreArticulo: string;
  contenido: string;
  genero: string;
  tipo: TipoRevista;
  autor: string;
}

export interface Comentario {
  id: number;
  articuloId: number;
  autor: string;
  contenido: string;
  fecha: Date;
}
