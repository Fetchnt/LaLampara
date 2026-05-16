import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Auth } from '../../core/services/auth';
import { Rol } from '../../core/models/usuario';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css'],
  standalone: false,
})
export class Navbar implements OnInit, OnDestroy {
  sesionActiva: boolean = false;
  private sub!: Subscription;

  constructor(private authService: Auth) {}

  ngOnInit(): void {
    this.sub = this.authService.logueado$.subscribe((valor) => {
      this.sesionActiva = valor;
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  getRol(): string {
    return this.authService.getRol() || '';
  }

  getUsuario(): string {
    return this.authService.getUsuario() || '';
  }

  tieneAcceso(roles: Rol[]): boolean {
    return this.authService.tieneRol(roles);
  }

  onLogout(): void {
    this.authService.logout();
  }
}
