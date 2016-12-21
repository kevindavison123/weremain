import { WeremainPage } from './app.po';

describe('weremain App', function() {
  let page: WeremainPage;

  beforeEach(() => {
    page = new WeremainPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
