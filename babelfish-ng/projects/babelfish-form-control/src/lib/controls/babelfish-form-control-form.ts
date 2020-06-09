import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';
import {BabelfishFormGroup} from '../babelfish-form-group.class';

export class BabelfishFormForm extends BabelfishFormControl {

  formGroup: BabelfishFormGroup;

  constructor(formGroup, controlStyle: string = "") {
    super(BabelfishFormControlTypes.FORM, '', '', '', [], controlStyle, []);
    this.formGroup = formGroup;
  }
}
