import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from '../../core/services/auth';
import { Rol } from '../../core/models/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
  standalone: false,
})
export class Login {
  // Login
  usuarioInput: string = '';
  contraseniaInput: string = '';
  error: boolean = false;

  // Registro
  modoRegistro: boolean = false;
  nuevoUsuario: string = '';
  nuevaContrasenia: string = '';
  nuevoRol: Rol | '' = '';
  errorRegistro: string = '';
  exitoRegistro: boolean = false;

  constructor(
    private authService: Auth,
    private router: Router,
  ) {}

  onLogin(): void {
    const exito = this.authService.login(this.usuarioInput, this.contraseniaInput);
    if (exito) {
      this.error = false;
      this.router.navigate(['/home']);
    } else {
      this.error = true;
    }
  }

  onRegistro(): void {
    this.errorRegistro = '';
    this.exitoRegistro = false;

    if (!this.nuevoUsuario.trim()) {
      this.errorRegistro = 'El usuario no puede estar vacío.';
      return;
    }
    if (!this.nuevaContrasenia.trim()) {
      this.errorRegistro = 'La contraseña no puede estar vacía.';
      return;
    }
    if (!this.nuevoRol) {
      this.errorRegistro = 'Debes seleccionar un tipo de cuenta.';
      return;
    }

    const exito = this.authService.registrar(
      this.nuevoUsuario,
      this.nuevaContrasenia,
      this.nuevoRol,
    );

    if (exito) {
      this.exitoRegistro = true;
      this.nuevoUsuario = '';
      this.nuevaContrasenia = '';
      this.nuevoRol = '';
      setTimeout(() => this.toggleModo(), 1500);
    } else {
      this.errorRegistro = 'Ese nombre de usuario ya existe.';
    }
  }

  toggleModo(): void {
    this.modoRegistro = !this.modoRegistro;
    this.error = false;
    this.errorRegistro = '';
    this.exitoRegistro = false;
  }
}
