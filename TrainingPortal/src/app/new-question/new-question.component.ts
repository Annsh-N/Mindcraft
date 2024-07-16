import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-new-question',
  templateUrl: './new-question.component.html',
  styleUrls: ['./new-question.component.css']
})
export class NewQuestionComponent {
  @Input() questionType : String = 'Add'
  currentOption : string = '';
  emptyMap = new Map();

  add() {
    this.questionType = this.currentOption;
  }
}
