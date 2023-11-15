import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ModalPerfilPage } from './modal-perfil.page';

const routes: Routes = [
  {
    path: '',
    component: ModalPerfilPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ModalPerfilPageRoutingModule {}
