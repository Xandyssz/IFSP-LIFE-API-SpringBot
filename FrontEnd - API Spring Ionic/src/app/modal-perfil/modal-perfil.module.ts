import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ModalPerfilPageRoutingModule } from './modal-perfil-routing.module';

import { ModalPerfilPage } from './modal-perfil.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ModalPerfilPageRoutingModule
  ],
  declarations: [ModalPerfilPage]
})
export class ModalPerfilPageModule {}
