import { FormGroup } from '@angular/forms';
import {BabelfishFormInput} from './babelfish-form-control.component';

export class CdrFormGroup extends FormGroup {

    private _submitted = false;

    hideAllControls() {
        Object.keys(this.controls).forEach((key) => (this.controls[key] as BabelfishFormInput).isVisible = false);
    }

    showControl(controlName: string): void {
        (this.controls[controlName] as BabelfishFormInput).isVisible = true;
    }

    get submitted() { return this._submitted; };

    setSubmitted(submitted) {
        this._submitted = submitted;
    }

}
