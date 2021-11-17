package com.quangsang.guavademo.guavas;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;


public class GuavaTester4 {

    public static void main(String[] args) {
        GuavaTester4 tester4 = new GuavaTester4();
        tester4.whenFilterWithIterables_thenFiltered();
        tester4.whenFilterCollectionWithCustomPredicate_thenFiltered();
        tester4.whenFilterUsingMultiplePredicates_thenFiltered();
        tester4.whenRemoveNullFromCollection_thenRemoved();
        tester4.whenCheckingIfAllElementsMatchACondition_thenCorrect();
        tester4.whenTransformWithIterables_thenTransformed();
        tester4.whenTransformWithCollections2_thenTransformed();
        /*multilmap*/

    }

    public void whenFilterWithIterables_thenFiltered() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Iterable<String> result
                = Iterables.filter(names, Predicates.containsPattern("A"));
        System.out.println(result);
    }

    public void whenFilterCollectionWithCustomPredicate_thenFiltered() {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("A") || input.startsWith("J");
            }
        };

        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Collection<String> result = Collections2.filter(names, predicate);
        System.out.println(result);
    }

    public void whenFilterUsingMultiplePredicates_thenFiltered() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Collection<String> result = Collections2.filter(names,
                Predicates.or(Predicates.containsPattern("J"),
                        Predicates.not(Predicates.containsPattern("a"))));
        System.out.println(result);
    }

    public void whenRemoveNullFromCollection_thenRemoved() {
        List<String> names =
                Lists.newArrayList("John", null, "Jane", null, "Adam", "Tom");
        Collection<String> result =
                Collections2.filter(names, Predicates.notNull());

        System.out.println(result);
    }

    public void whenCheckingIfAllElementsMatchACondition_thenCorrect() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");

        boolean result = Iterables.all(names, Predicates.containsPattern("n|m"));

        result = Iterables.all(names, Predicates.containsPattern("a"));
        System.out.println(result);
    }

    public void whenTransformWithIterables_thenTransformed() {
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        };

        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Iterable<Integer> result = Iterables.transform(names, function);
        System.out.println(result);
    }

    public void whenTransformWithCollections2_thenTransformed() {
        Function<String,Integer> func = new Function<String,Integer>(){
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        };

        List<String> names =
                Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Collection<Integer> result = Collections2.transform(names, func);

        System.out.println(result.size());
        result.remove(3);
        System.out.println(result);
    }

}
