import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from '../../core/services/auth';
import { Rol } from '../../core/models/usuario';

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
  styleUrl: './home.css',
  standalone: false,
})
export class Home {
  constructor(
    private authService: Auth,
    private router: Router,
  ) {}

  getUsuario(): string {
    return this.authService.getUsuario() || '';
  }

  tieneAcceso(roles: Rol[]): boolean {
    return this.authService.tieneRol(roles);
  }

  irA(ruta: string): void {
    this.router.navigate([ruta]);
  }
}
