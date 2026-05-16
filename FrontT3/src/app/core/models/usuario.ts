export interface Usuario {
  id: number;
  usuario: string;
  contrasenia: string;
  rol: Rol;
}

export type Rol = 'SIN_AUTENTICAR' | 'USUARIO' | 'EDITOR' | 'COMENTADOR' | 'ADMIN';
