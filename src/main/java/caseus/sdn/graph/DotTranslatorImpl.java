package caseus.sdn.graph;

import one.util.streamex.StreamEx;

import java.util.List;
import java.util.stream.Collectors;

public class DotTranslatorImpl implements DotTranslator {

    @Override
    public List<String> translate(Graph graph) {
        List<String> nodes = graph.getNodes()
                                  .stream()
                                  .map(node -> String.format("\"%s\" [label=%s]", node.getId(), node.getSimpleName()))
                                  .collect(Collectors.toList());
        List<String> relations = graph.getRelations()
                                      .stream()
                                      .map(relation -> String.format("\"%s\" -> \"%s\" [label=%s, style=%s]",
                                                                     relation.getNodeFrom(),
                                                                     relation.getNodeTo(),
                                                                     relation.getName(),
                                                                     relation.getRelationType() == RelationType.COLLECTION
                                                                             ? "solid"
                                                                             : "dotted"))
                                      .collect(Collectors.toList());
        return StreamEx.of("@startuml", "digraph graphModel {")
                       .append(nodes)
                       .append(relations)
                       .append("}")
                       .append("@enduml")
                       .collect(Collectors.toList());
    }

}
