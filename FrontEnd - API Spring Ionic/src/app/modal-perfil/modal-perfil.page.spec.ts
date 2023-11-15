import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ModalPerfilPage } from './modal-perfil.page';

describe('ModalPerfilPage', () => {
  let component: ModalPerfilPage;
  let fixture: ComponentFixture<ModalPerfilPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(ModalPerfilPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
