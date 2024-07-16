import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuleEditorComponent } from './module-editor.component';

describe('ModuleEditorComponent', () => {
  let component: ModuleEditorComponent;
  let fixture: ComponentFixture<ModuleEditorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModuleEditorComponent]
    });
    fixture = TestBed.createComponent(ModuleEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
