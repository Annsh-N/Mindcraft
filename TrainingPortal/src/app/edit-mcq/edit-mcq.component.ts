import { ChangeDetectorRef, Component, ElementRef, Input, OnInit, QueryList, ViewChildren } from '@angular/core';
import { delay } from 'rxjs';

@Component({
  selector: 'app-edit-mcq',
  templateUrl: './edit-mcq.component.html',
  styleUrls: ['./edit-mcq.component.css']
})
export class EditMcqComponent {
  @Input() questionText : string = '';
  @Input() options : string[] = [];
  @Input() correctAnswer : string = '';
  newOption = '';
  @Input() editMode: boolean = false;
  changeType : string = 'MCQ';

  constructor() {
    this.questionText = 'Which of the following statements is NOT a benefit of implementing Multi-Factor Authentication (MFA)?';
    this.options = [
      'Enhanced Security',
      'Reduced Risk of Phishing and Credential Theft',
      'Simplified User Experience',
      'Compliance with Regulations'
    ];
    this.correctAnswer = 'Enhanced Security';
  }

  @ViewChildren('input') inputs: QueryList<ElementRef>;
  @ViewChildren('button') buttons: QueryList<ElementRef>;

  deleteQuestion() {

  }

  changeTypeFn(changeType: string) {
    this.changeType = changeType;
  }

  removeOption(i: number) {
    if (this.options[i] == '') {
      if (this.options.findIndex(s => s == this.correctAnswer) == -1) {
        this.correctAnswer = '';
      }
      this.options.splice(i, 1);
    }
  }

  trackByFn(index: number, item: string): number {
    return index;
  }

  addOption() {
    this.newOption = "Option " + (this.options.length + 1);
    this.options.push(this.newOption);
    this.newOption = '';
    setTimeout(() => {
      this.focusLastInput();
    });
  }

  focusLastInput() {
    const lastInput = this.inputs.last;
    if (lastInput) {
      lastInput.nativeElement.focus();
    }
  }

  save() {
    this.editMode = false;
  }

}
