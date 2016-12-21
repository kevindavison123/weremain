import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  template: '<h1>{{title}}</h1><div>Hello world!</div>'
})
export class AppComponent {
  title = 'app works!';
}
