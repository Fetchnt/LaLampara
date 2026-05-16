import { Component } from '@angular/core';
import { Auth } from '../../core/services/auth';
import { Usuario } from '../../core/models/usuario';
import { Articulo, TipoRevista } from '../../core/models/articulo';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.html',
  styleUrl: './admin.css',
  standalone: false,
})
export class Admin {
  mensajeArticulo: string = '';

  nuevoArticulo: Articulo = {
    id: 0,
    nombreArticulo: '',
    contenido: '',
    genero: '',
    tipo: 'NOTICIA',
    autor: '',
  };

  constructor(private authService: Auth) {}

  getUsuarios(): Usuario[] {
    return this.authService.getUsuarios();
  }

  cambiarRol(usuario: Usuario): void {
    this.authService.actualizarRol(usuario.id, usuario.rol);
  }

  eliminarUsuario(id: number): void {
    this.authService.eliminarUsuario(id);
  }

  crearArticulo(): void {
    if (
      !this.nuevoArticulo.nombreArticulo.trim() ||
      !this.nuevoArticulo.contenido.trim() ||
      !this.nuevoArticulo.genero.trim()
    ) {
      return;
    }

    this.nuevoArticulo.autor = this.authService.getUsuario() || 'Admin';
    this.mensajeArticulo = `Artículo "${this.nuevoArticulo.nombreArticulo}" creado correctamente.`;

    this.nuevoArticulo = {
      id: 0,
      nombreArticulo: '',
      contenido: '',
      genero: '',
      tipo: 'NOTICIA',
      autor: '',
    };

    setTimeout(() => (this.mensajeArticulo = ''), 2500);
  }
}
