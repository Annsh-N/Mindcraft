import { Component, ElementRef, Input, QueryList, ViewChildren } from '@angular/core';

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrls: ['./edit-order.component.css']
})
export class EditOrderComponent{
  newOption = '';
  @Input() editMode: boolean = false;
  changeType : string = 'Order Items';
  @Input() questionText : string = '';
  @Input() options : string[] = [];

  constructor() {
    this.questionText = 'Order the steps to effectively implement Multi-Factor Authentication (MFA):';
    this.options = ['Evaluate MFA Solutions', 'Educate Users', 'Integrate with Existing Systems', 'Monitor and Maintain'];
  }

  @ViewChildren('input') inputs: QueryList<ElementRef>;

  deleteQuestion() {}

  save() {
    this.editMode = false;
  }

  changeTypeFn(changeType: string) {
    this.changeType = changeType;
  }

  removeOption(i: number) {
    if (this.options[i] == '' && this.options.length > 2) {
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

}


