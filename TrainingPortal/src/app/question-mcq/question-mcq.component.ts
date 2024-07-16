import { Component, OnInit } from '@angular/core';
import { QuestionComponent } from '../question/question.component';

@Component({
  selector: 'app-question-mcq',
  templateUrl: './question-mcq.component.html',
  styleUrls: ['./question-mcq.component.css']
})
export class QuestionMCQComponent extends QuestionComponent implements OnInit {

  options = [];
  shuffledOptions = [];

  constructor() {
    super();
    this.questionText = 'Which of the following statements is NOT a benefit of implementing Multi-Factor Authentication (MFA)?';
    this.options = [
      'Enhanced Security',
      'Reduced Risk of Phishing and Credential Theft',
      'Simplified User Experience',
      'Compliance with Regulations'
    ];
  }

  ngOnInit(): void {
    this.shuffledOptions = this.options.slice().sort(() => Math.random() - 0.5);
  }

  
}
