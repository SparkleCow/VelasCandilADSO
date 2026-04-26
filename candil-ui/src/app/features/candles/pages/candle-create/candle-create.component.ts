import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import {
  CATEGORIES,
  CategoryEnum,
  FEATURES,
  MATERIALS,
  MaterialEnum,
  FeatureEnum,
  CandleRequest
} from '../../../../shared/models/candle.models';

import {
  IngredientsEnum,
  INGREDIENTS_ENUM_LIST,
  IngredientRequest
} from '../../../../shared/models/ingredient.models';

import { CandleService } from '../../../../core/services/candle.service';


type IngredientFormGroup = FormGroup<{
  name: FormControl<IngredientsEnum>;
  amount: FormControl<number>;
}>;

@Component({
  selector: 'app-candle-create',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
    MatSnackBarModule
  ],
  templateUrl: './candle-create.component.html',
  styleUrl: './candle-create.component.css'
})
export class CandleCreateComponent {
  private readonly fb = inject(FormBuilder);
  private readonly candleService = inject(CandleService);
  private readonly snackBar = inject(MatSnackBar);
  private readonly router = inject(Router);

  readonly categories = CATEGORIES;
  readonly materials = MATERIALS;
  readonly features = FEATURES;

  // Mock local to quickly validate UI/UX and payload shape in isolation
  readonly ingredientOptionsMock: IngredientsEnum[] = INGREDIENTS_ENUM_LIST;

  readonly creating = signal(false);

  readonly imageUrlControl = new FormControl('', {
    nonNullable: true,
    validators: [Validators.required]
  });

  readonly ingredientDraftForm = this.fb.nonNullable.group({
    name: this.fb.nonNullable.control<IngredientsEnum | ''>('', Validators.required),
    amount: this.fb.nonNullable.control<number | null>(null, [
      Validators.required,
      Validators.min(0.01)
    ])
  });

  readonly form = this.fb.nonNullable.group({
    name: this.fb.nonNullable.control('', [Validators.required, Validators.maxLength(120)]),
    description: this.fb.nonNullable.control('', [Validators.required, Validators.maxLength(900)]),
    principalImage: this.fb.nonNullable.control('', [Validators.required]),
    stock: this.fb.nonNullable.control<number | null>(null, [Validators.required, Validators.min(0)]),
    materialEnums: this.fb.nonNullable.control<MaterialEnum[]>([], [Validators.required]),
    featureEnums: this.fb.nonNullable.control<FeatureEnum[]>([], [Validators.required]),
    categories: this.fb.nonNullable.control<CategoryEnum[]>([], [Validators.required]),
    images: this.fb.array<FormControl<string>>([]),
    ingredients: this.fb.array<IngredientFormGroup>([])
  });

  get imagesArray(): FormArray<FormControl<string>> {
    return this.form.controls.images;
  }

  get ingredientsArray(): FormArray<IngredientFormGroup> {
    return this.form.controls.ingredients;
  }

  addImage(): void {
    if (this.imageUrlControl.invalid) {
      this.imageUrlControl.markAsTouched();
      return;
    }

    const rawValue = this.imageUrlControl.value.trim();
    if (!rawValue) return;

    this.imagesArray.push(
      this.fb.nonNullable.control(rawValue, [Validators.required])
    );
    this.imageUrlControl.reset();
  }

  removeImage(index: number): void {
    this.imagesArray.removeAt(index);
  }

  addIngredient(): void {
    if (this.ingredientDraftForm.invalid) {
      this.ingredientDraftForm.markAllAsTouched();
      return;
    }

    const { name, amount } = this.ingredientDraftForm.getRawValue();
    if (!name || amount === null) return;

    this.ingredientsArray.push(this.buildIngredientGroup(name, amount));
    this.ingredientDraftForm.reset({
      name: '',
      amount: null
    });
  }

  removeIngredient(index: number): void {
    this.ingredientsArray.removeAt(index);
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload: CandleRequest = {
      ...this.form.getRawValue(),
      stock: this.form.getRawValue().stock ?? 0
    };

    this.creating.set(true);
    this.candleService.create(payload).subscribe({
      next: () => {
        this.creating.set(false);
        this.snackBar.open('Vela creada correctamente', 'Cerrar', { duration: 2500 });
        this.router.navigate(['/candles']);
      },
      error: () => {
        this.creating.set(false);
        this.snackBar.open('No se pudo crear la vela. Revisa los datos e intenta de nuevo.', 'Cerrar', { duration: 3500 });
      }
    });
  }

  formatLabel(value: string): string {
    return value.replace(/_/g, ' ');
  }

  private buildIngredientGroup(name: IngredientsEnum, amount: number): IngredientFormGroup {
    return this.fb.nonNullable.group({
      name: this.fb.nonNullable.control<IngredientsEnum>(name, Validators.required),
      amount: this.fb.nonNullable.control<number>(amount, [Validators.required, Validators.min(0.01)])
    });
  }
}
