import { TestBed } from '@angular/core/testing';

import { DoctorauthgaurdGuard } from './doctorauthgaurd.guard';

describe('DoctorauthgaurdGuard', () => {
  let guard: DoctorauthgaurdGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(DoctorauthgaurdGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
