import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Auth } from '../services/auth';
import { Rol } from '../models/usuario';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(
    private authService: Auth,
    private router: Router,
  ) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const rolesPermitidos: Rol[] = route.data['roles'];
    const tieneAcceso = this.authService.tieneRol(rolesPermitidos);

    if (tieneAcceso) {
      return true;
    }

    this.router.navigate(['/home']);
    return false;
  }
}
