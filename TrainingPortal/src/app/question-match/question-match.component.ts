import { AfterViewInit, Component } from '@angular/core';
import { QuestionComponent } from '../question/question.component';

@Component({
  selector: 'app-question-match',
  templateUrl: './question-match.component.html',
  styleUrls: ['./question-match.component.css']
})
export class QuestionMatchComponent extends QuestionComponent implements AfterViewInit {

  titleMap : Map<String, String>;
  optionsMap : Map<String, String> = new Map();
  leftOptions = [];
  rightOptions = [];

  constructor() {
    super();
    this.questionText = 'Match the MFA benefits with their descriptions:';
    this.titleMap = new Map([['Benefits', 'Descriptions']]);
    this.optionsMap = new Map([
      [ 'Enhanced Security', 'Requires attackers to breach multiple factors to gain access.' ],
      [ 'Reduced Risk of Phishing and Credential Theft', 'Reduces the effectiveness of common attack vectors.' ],
      [ 'Compliance with Regulations', 'Helps organizations comply with standards like GDPR, HIPAA, and PCI-DSS.' ],
      [ 'Protection Against Automated Attacks', 'Deters attackers from attempting brute force attacks.' ]
    ]);
  }

  ngOnInit(): void {
    this.leftOptions = (Array.from(this.optionsMap.keys())).slice().sort(() => Math.random() - 0.5);
    this.rightOptions = (Array.from(this.optionsMap.values())).slice().sort(() => Math.random() - 0.5);
  }

  ngAfterViewInit(): void {
    this.initializeDragAndDrop();
  }

  initializeDragAndDrop(): void {
    const plugs = document.querySelectorAll('.plug');
    const connectors = document.querySelectorAll('.connector');

    plugs.forEach(plug => {
      plug.addEventListener('dragstart', this.dragStart);
    });

    connectors.forEach(connector => {
      connector.addEventListener('dragover', this.dragOver);
      connector.addEventListener('drop', this.drop);
    });
  }

  dragStart(event: DragEvent): void {
    const target = event.target as HTMLElement;
    event.dataTransfer?.setData('currPlug', target.dataset.step!);
  }

  dragOver(event: DragEvent): void {
    event.preventDefault();
  }

  drop(event: DragEvent): void {
    event.preventDefault();
    const step = event.dataTransfer?.getData('currPlug');
    const plug = document.querySelector(`.plug[data-step='${step}']`);
    if (event.target && event.target instanceof HTMLElement && event.target.classList.contains('connector') && event.target.innerText === "") {
      const connectedPlug = plug?.cloneNode(true) as HTMLElement;
      (connectedPlug?.children[1] as HTMLImageElement).src = 'assets/images/connected-plug.jpg';
      (event.target as HTMLElement).appendChild(connectedPlug);
      plug.classList.add('disabled');
      connectedPlug.classList.add('dropped');
    }
    console.log('Matched ', plug?.textContent, ' with ', (event.target as HTMLElement).textContent);
  }

  resetOrder(): void {
    // const draggablesContainer = document.getElementById('draggable-elements');
    const connectors = document.querySelectorAll('.connector');
    const plugs = document.querySelectorAll('.plug');
    plugs.forEach(plug => {
      if (plug.classList.contains('disabled')) {
        plug.classList.remove('disabled');
      }
    });
    connectors.forEach(connector => {
      if (connector.firstElementChild && connector.firstElementChild.classList.contains('dropped')) {
        (connector as HTMLElement).removeChild(connector.firstChild);
      }
    });
    // Clear saved order from local storage if necessary
    // localStorage.removeItem('mfaOrder');
  }
}
