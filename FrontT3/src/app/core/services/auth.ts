import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Usuario, Rol } from '../models/usuario';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private apiUrl = 'http://localhost:8080/api/auth';

  private usuariosMock: Usuario[] = [
    { id: 1, usuario: 'admin', contrasenia: '1234', rol: 'ADMIN' },
    { id: 2, usuario: 'editor', contrasenia: '1234', rol: 'EDITOR' },
    { id: 3, usuario: 'comentador', contrasenia: '1234', rol: 'COMENTADOR' },
    { id: 4, usuario: 'usuario', contrasenia: '1234', rol: 'USUARIO' },
    { id: 5, usuario: 'invitado', contrasenia: '1234', rol: 'SIN_AUTENTICAR' },
  ];

  logueado$ = new BehaviorSubject<boolean>(!!localStorage.getItem('token'));

  constructor(private router: Router) {}

  login(usuario: string, contrasenia: string): boolean {
    const encontrado = this.usuariosMock.find(
      (u) => u.usuario === usuario && u.contrasenia === contrasenia,
    );

    if (encontrado) {
      const tokenFalso = this.generarTokenMock(encontrado);
      localStorage.setItem('token', tokenFalso);
      localStorage.setItem('rol', encontrado.rol);
      localStorage.setItem('usuario', encontrado.usuario);
      this.logueado$.next(true);
      return true;
    }

    return false;
  }

  registrar(usuario: string, contrasenia: string, rol: Rol): boolean {
    const existe = this.usuariosMock.find((u) => u.usuario === usuario);
    if (existe) return false;

    const nuevoId = this.usuariosMock.length + 1;
    this.usuariosMock.push({ id: nuevoId, usuario, contrasenia, rol });
    return true;
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('rol');
    localStorage.removeItem('usuario');
    this.logueado$.next(false);
    this.router.navigate(['/login']);
  }

  getRol(): Rol | null {
    return localStorage.getItem('rol') as Rol | null;
  }

  getUsuario(): string | null {
    return localStorage.getItem('usuario');
  }

  isLogueado(): boolean {
    return !!localStorage.getItem('token');
  }

  tieneRol(roles: Rol[]): boolean {
    const rol = this.getRol();
    return rol ? roles.includes(rol) : false;
  }

  getUsuarios(): Usuario[] {
    return this.usuariosMock;
  }

  actualizarRol(id: number, rol: Rol): void {
    const usuario = this.usuariosMock.find((u) => u.id === id);
    if (usuario) usuario.rol = rol;
  }

  eliminarUsuario(id: number): void {
    this.usuariosMock = this.usuariosMock.filter((u) => u.id !== id);
  }

  private generarTokenMock(usuario: Usuario): string {
    const header = btoa(JSON.stringify({ alg: 'HS256', typ: 'JWT' }));
    const payload = btoa(
      JSON.stringify({ sub: usuario.usuario, rol: usuario.rol, iat: Date.now() }),
    );
    const firma = btoa('firma-mock');
    return `${header}.${payload}.${firma}`;
  }
}
