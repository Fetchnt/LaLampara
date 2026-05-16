import { Component } from '@angular/core';
import { Auth } from '../../core/services/auth';
import { Articulo, Comentario } from '../../core/models/articulo';

@Component({
  selector: 'app-horoscopos',
  templateUrl: './horoscopos.html',
  styleUrl: './horoscopos.css',
  standalone: false,
})
export class Horoscopos {
  articulos: Articulo[] = [
    {
      id: 10,
      nombreArticulo: 'Aries — Semana del 15 al 21',
      contenido:
        'Esta semana trae grandes oportunidades laborales. Confía en tu instinto y actúa con determinación.',
      genero: '♈ Aries',
      tipo: 'HOROSCOPO',
      autor: 'Astróloga María',
    },
    {
      id: 11,
      nombreArticulo: 'Tauro — Semana del 15 al 21',
      contenido:
        'El amor llama a tu puerta. Mantén la calma en situaciones de tensión y todo fluirá naturalmente.',
      genero: '♉ Tauro',
      tipo: 'HOROSCOPO',
      autor: 'Astróloga María',
    },
    {
      id: 12,
      nombreArticulo: 'Géminis — Semana del 15 al 21',
      contenido:
        'Tu creatividad está en su punto más alto. Aprovecha este momento para iniciar nuevos proyectos.',
      genero: '♊ Géminis',
      tipo: 'HOROSCOPO',
      autor: 'Astróloga María',
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
