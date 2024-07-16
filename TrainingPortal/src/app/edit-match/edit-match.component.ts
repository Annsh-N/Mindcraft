import { Component, ElementRef, Input, QueryList, ViewChildren } from '@angular/core';
import { QuestionComponent } from '../question/question.component';

@Component({
  selector: 'app-edit-match',
  templateUrl: './edit-match.component.html',
  styleUrls: ['./edit-match.component.css']
})
export class EditMatchComponent{
  @Input() titleMap : Map<String, String>;
  @Input() optionsMap : Map<String, String> = new Map();
  leftOptions = [];
  rightOptions = [];
  @Input() editMode : boolean = false;
  @Input() questionText = '';
  changeType : string = 'Match the Following';
  newOption : string = '';
  newAnswer : string = '';
  uniqueAlert : boolean = false;
  blankAlert : boolean = false;
  @Input() new : boolean = false;

  constructor() {
      this.questionText = 'Match the MFA benefits with their descriptions:';
      this.titleMap = new Map([['Benefits', 'Descriptions']]);
      this.optionsMap = new Map([
        [ 'Enhanced Security', 'Requires attackers to breach multiple factors to gain access.' ],
        [ 'Reduced Risk of Phishing and Credential Theft', 'Reduces the effectiveness of common attack vectors.' ],
        [ 'Compliance with Regulations', 'Helps organizations comply with standards like GDPR, HIPAA, and PCI-DSS.' ],
        [ 'Protection Against Automated Attacks', 'Deters attackers from attempting brute force attacks.' ]
      ]);
  }

  @ViewChildren('option') options: QueryList<ElementRef>;

  ngOnInit(): void {
    this.leftOptions = (Array.from(this.optionsMap.keys())).slice();
    this.rightOptions = (Array.from(this.optionsMap.values())).slice();
  }

  addOption() {
    this.newOption = "Option " + (this.leftOptions.length + 1);
    this.newAnswer = "Answer " + (this.leftOptions.length + 1);
    this.leftOptions.push(this.newOption);
    this.rightOptions.push(this.newAnswer);
    this.newOption = '';
    this.newAnswer = '';
    setTimeout(() => {
      this.focusLastInput();
    });
  }

  focusLastInput() {
    const lastOption = this.options.last;
    if (lastOption) {
      lastOption.nativeElement.focus();
    }
  }

  changeTypeFn(changeType: string) {
    this.changeType = changeType;
  }

  trackByFn(index: number, item: string): number {
    return index;
  }

  removeOption(i: number) {
    if (this.leftOptions[i] == '' && this.rightOptions[i] == '' && this.leftOptions.length > 2) {
      this.leftOptions.splice(i, 1);
      this.rightOptions.splice(i, 1);
    }
  }

  deleteQuestion() {

  }

  save() {
    this.uniqueAlert = false;
    this.blankAlert = false;
    const leftSet = new Set(this.leftOptions);
    const rightSet = new Set(this.rightOptions);
    if (this.leftOptions.includes('') || this.rightOptions.includes('')) {
      this.blankAlert = true;
    } else if (leftSet.size !== this.leftOptions.length || rightSet.size !== this.rightOptions.length) {
      this.uniqueAlert = true;
    } else {
      this.editMode = false;
      this.optionsMap = new Map();
      for (let i = 0; i < this.leftOptions.length; i++) {
        this.optionsMap.set(this.leftOptions[i], this.rightOptions[i]);
      }
    }
  }
}
