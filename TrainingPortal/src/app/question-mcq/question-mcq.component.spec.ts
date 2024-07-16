import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionMCQComponent } from './question-mcq.component';

describe('QuestionMCQComponent', () => {
  let component: QuestionMCQComponent;
  let fixture: ComponentFixture<QuestionMCQComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionMCQComponent]
    });
    fixture = TestBed.createComponent(QuestionMCQComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
