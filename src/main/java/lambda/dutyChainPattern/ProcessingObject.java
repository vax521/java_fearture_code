package lambda.dutyChainPattern;

public abstract  class ProcessingObject<T> {
    protected ProcessingObject<T> processingObject;

    public void setProcessingObject(ProcessingObject<T> processingObject){
            this.processingObject = processingObject;
    }

    public T handle(T input){
        T r = handleTask(input);
        if(processingObject != null){
            return processingObject.handle(r);
        }
        return r;
    }
    abstract protected T handleTask(T input);

}
