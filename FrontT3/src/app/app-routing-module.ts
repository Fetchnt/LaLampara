import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Login } from './pages/login/login';
import { Home } from './pages/home/home';
import { Noticias } from './pages/noticias/noticias';
import { Horoscopos } from './pages/horoscopos/horoscopos';
import { Admin } from './pages/admin/admin';

import { AuthGuard } from './core/guards/auth-guard';
import { RoleGuard } from './core/guards/role-guard';

const routes: Routes = [
  // Ruta por defecto
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  // Login — acceso libre
  { path: 'login', component: Login },

  // Home — solo usuarios logueados
  {
    path: 'home',
    component: Home,
    //canActivate: [AuthGuard],
  },

  // Noticias — USUARIO, EDITOR, COMENTADOR, ADMIN
  {
    path: 'noticias',
    component: Noticias,
    //canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['USUARIO', 'EDITOR', 'COMENTADOR', 'ADMIN'] },
  },

  // Horoscopos — USUARIO, EDITOR, COMENTADOR, ADMIN
  {
    path: 'horoscopos',
    component: Horoscopos,
    //canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['USUARIO', 'EDITOR', 'COMENTADOR', 'ADMIN'] },
  },

  // Admin — solo ADMIN
  {
    path: 'admin',
    component: Admin,
    //canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['ADMIN'] },
  },

  // Cualquier ruta desconocida vuelve al login
  { path: '**', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
