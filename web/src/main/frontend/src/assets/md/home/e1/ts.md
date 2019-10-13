```typescript
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-register-speaker',
  templateUrl: './register-speaker.component.html',
  styleUrls: ['./register-speaker.component.css']
})
export class RegisterSpeakerComponent implements OnInit {

  showDetails: boolean;

  ngOnInit() {
  }

  onStrengthChanged(strength: number) {
    console.log('password strength = ', strength);
  }
}
```
