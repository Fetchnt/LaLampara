import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Login } from './pages/login/login';
import { Home } from './pages/home/home';
import { Noticias } from './pages/noticias/noticias';
import { Horoscopos } from './pages/horoscopos/horoscopos';
import { Admin } from './pages/admin/admin';
import { Navbar } from './shared/navbar/navbar';
import { JwtInterceptor } from './core/interceptors/jwt-interceptor';
import { RouterModule } from '@angular/router';

  @NgModule({
    declarations: [App, Login, Home, Noticias, Horoscopos, Admin, Navbar],
    imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule, RouterModule],
    providers: [
      provideBrowserGlobalErrorListeners(),
      {
        provide: HTTP_INTERCEPTORS,
        useClass: JwtInterceptor,
        multi: true,
      },
    ],
    bootstrap: [App],
  })
  export class AppModule {}

