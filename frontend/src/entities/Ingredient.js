export class Ingredient {

    /**
     * 
     * @param {IngredientBuilder} ingredientBuilder 
     */
    constructor(ingredientBuilder) {
        this.ingredientId = ingredientBuilder.ingredientId;
        this.name = ingredientBuilder.name;
        this.amount = ingredientBuilder.amount;
        this.unitId = ingredientBuilder.unitId;
        this.unitAbbreviation = ingredientBuilder.unitAbbreviation;
    }
}

export class IngredientBuilder {

    setIngredientId(ingredientId) {
        this.ingredientId = ingredientId;
        switch (ingredientId) {
            case 1:
                this.name = 'Ägg';
                break;
            case 2:
                this.name = 'Mjölk';
                break;
            case 3:
                this.name = 'Salt';
                break;
            case 4:
                this.name = 'Smör';
                break;
            case 5:
                this.name = 'Vetemjöl';
                break;
            default:
                this.name = 'ERROR';
                break;
        }
        return this;
    }

    setAmount(amount) {
        this.amount = amount;
        return this;
    }

    setUnitId(unitId) {
        this.unitId = unitId;
        switch (unitId) {
            case 1:
                this.unitAbbreviation = 'st';
                break;
            case 2:
                this.unitAbbreviation = 'kg';
                break;
            case 3:
                this.unitAbbreviation = 'hg';
                break;
            case 4:
                this.unitAbbreviation = 'l';
                break;
            case 5:
                this.unitAbbreviation = 'dl';
                break;
            case 6:
                this.unitAbbreviation = 'cl';
                break;
            case 7:
                this.unitAbbreviation = 'ml';
                break;
            case 8:
                this.unitAbbreviation = 'msk';
                break;
            case 9:
                this.unitAbbreviation = 'tsk';
                break;
            case 10:
                this.unitAbbreviation = 'krm';
                break;
            case 11:
                this.unitAbbreviation = 'kkp';
                break;
            case 12:
                this.unitAbbreviation = 'glas';
                break;
            default:
                this.unitAbbreviation = 'ERROR';
                break;
        }
        return this;
    }

    build() {
        return new Ingredient(this);
    }
}