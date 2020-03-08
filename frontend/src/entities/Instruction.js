export class Instruction{
    
    /**
     * 
     * @param {InstructionBuilder} instructionBuilder 
     */
    constructor(instructionBuilder){
        this.orderIndex = instructionBuilder.orderIndex;
        this.description = instructionBuilder.description;
    }
}

export class InstructionBuilder{

    setOrderIndex(orderIndex){
        this.orderIndex = orderIndex;
        return this;
    }

    setDescription(description){
        this.description = description;
        return this;
    }

    build(){
        return new Instruction(this);
    }
}