package graphql.execution;

import graphql.ExecutionResult;
import graphql.ExecutionResultImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;


public abstract class AbstractAsyncExecutionStrategy extends ExecutionStrategy {

    public AbstractAsyncExecutionStrategy() {
    }

    public AbstractAsyncExecutionStrategy(DataFetcherExceptionHandler dataFetcherExceptionHandler) {
        super(dataFetcherExceptionHandler);
    }

    protected BiConsumer<List<ExecutionResult>, Throwable> handleResults(ExecutionContext executionContext, List<String> fieldNames, CompletableFuture<ExecutionResult> overallResult) {
        return handleResults(executionContext, fieldNames, overallResult, new LinkedHashMap<>());
    }

    protected BiConsumer<List<ExecutionResult>, Throwable> handleResults(ExecutionContext executionContext, List<String> fieldNames, CompletableFuture<ExecutionResult> overallResult, Map<String, Object> hasValueNodemap) {
        return (List<ExecutionResult> results, Throwable exception) -> {
            if (exception != null) {
                handleNonNullException(executionContext, overallResult, exception);
                return;
            }
            Map<String, Object> resolvedValuesByField = new LinkedHashMap<>();
            int ix = 0;
            for (String fieldName : fieldNames) {
                if (hasValueNodemap.containsKey(fieldName)) {
                    resolvedValuesByField.put(fieldName, hasValueNodemap.get(fieldName));
                } else {
                    resolvedValuesByField.put(fieldName, results.get(ix).getData());
                    ix++;
                }
            }

            resolvedValuesByField.putAll(hasValueNodemap);
            overallResult.complete(new ExecutionResultImpl(resolvedValuesByField, executionContext.getErrors()));
        };
    }
}
