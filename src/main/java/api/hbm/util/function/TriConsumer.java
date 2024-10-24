package api.hbm.util.function;

@FunctionalInterface
public interface TriConsumer<W, O, G> {
    void accept(W w, O o, G g);
}
