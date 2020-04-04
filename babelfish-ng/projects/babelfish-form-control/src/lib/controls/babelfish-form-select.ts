import {BabelfishFormControl} from './babelfish-form-control';
import {SelectItem} from 'primeng/api';
import {BabelfishFormControlTypes} from '../types/babelfish-control-types';

export class BabelfishFormSelect extends BabelfishFormControl {

  options: SelectItem[];

  constructor(defaultValue, label: string, validators = [], options: SelectItem[] = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.SELECT, defaultValue, label, validators, asyncValidators);

    this.options = options;
  }
}
