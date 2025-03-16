package dev.boze.client.utils.misc;

public interface ICopyable<T extends ICopyable<T>> {
   T set(T var1);

   T copy();
}
