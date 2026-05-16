import { Component } from '@angular/core';
import { Auth } from '../../core/services/auth';
import { Articulo, Comentario } from '../../core/models/articulo';
import { Rol } from '../../core/models/usuario';

@Component({
  selector: 'app-noticias',
  templateUrl: './noticias.html',
  styleUrl: './noticias.css',
  standalone: false,
})
export class Noticias {
  articulos: Articulo[] = [
    {
      id: 1,
      nombreArticulo: 'Avances en inteligencia artificial',
      contenido:
        'Los últimos modelos de IA superan expectativas en múltiples áreas del conocimiento humano.',
      genero: 'Tecnología',
      tipo: 'NOTICIA',
      autor: 'Editor Principal',
    },
    {
      id: 2,
      nombreArticulo: 'Cambio climático: nuevos reportes',
      contenido:
        'Científicos advierten sobre el acelerado derretimiento de glaciares en el hemisferio norte.',
      genero: 'Ciencia',
      tipo: 'NOTICIA',
      autor: 'Editor Principal',
    },
    {
      id: 3,
      nombreArticulo: 'Economía global en recuperación',
      contenido:
        'Los mercados internacionales muestran señales positivas tras años de incertidumbre.',
      genero: 'Economía',
      tipo: 'NOTICIA',
      autor: 'Editor Económico',
    },
  ];

  comentarios: { [articuloId: number]: string } = {};
  listaComentarios: Comentario[] = [];

  constructor(private authService: Auth) {}

  puedecomentar(): boolean {
    return this.authService.tieneRol(['COMENTADOR', 'EDITOR', 'ADMIN']);
  }

  comentar(articuloId: number): void {
    const texto = this.comentarios[articuloId];
    if (!texto || !texto.trim()) return;

    const nuevo: Comentario = {
      id: this.listaComentarios.length + 1,
      articuloId,
      autor: this.authService.getUsuario() || 'Anónimo',
      contenido: texto.trim(),
      fecha: new Date(),
    };

    this.listaComentarios.push(nuevo);
    this.comentarios[articuloId] = '';
  }

  getComentarios(articuloId: number): Comentario[] {
    return this.listaComentarios.filter((c) => c.articuloId === articuloId);
  }
}
