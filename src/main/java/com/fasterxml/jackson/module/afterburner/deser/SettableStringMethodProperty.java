package com.fasterxml.jackson.module.afterburner.deser;

import java.io.IOException;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;

public final class SettableStringMethodProperty
    extends OptimizedSettableBeanProperty<SettableStringMethodProperty>
{
    private static final long serialVersionUID = 1L;

    public SettableStringMethodProperty(SettableBeanProperty src,
            BeanPropertyMutator mutator, int index)
    {
        super(src, mutator, index);
    }

    public SettableStringMethodProperty(SettableStringMethodProperty src, JsonDeserializer<?> deser) {
        super(src, deser);
    }

    public SettableStringMethodProperty(SettableStringMethodProperty src, PropertyName name) {
        super(src, name);
    }
    
    @Override
    public SettableStringMethodProperty withName(PropertyName name) {
        return new SettableStringMethodProperty(this, name);
    }
    
    @Override
    public SettableStringMethodProperty withValueDeserializer(JsonDeserializer<?> deser) {
        return new SettableStringMethodProperty(this, deser);
    }
    
    @Override
    public SettableStringMethodProperty withMutator(BeanPropertyMutator mut) {
        return new SettableStringMethodProperty(_originalSettable, mut, _optimizedIndex);
    }

    /*
    /********************************************************************** 
    /* Deserialization
    /********************************************************************** 
     */
    
    // Copied from StdDeserializer.StringDeserializer:
    @Override
    public void deserializeAndSet(JsonParser jp, DeserializationContext ctxt,
            Object bean) throws IOException, JsonProcessingException
    {
        _propertyMutator.stringSetter(_originalSettable, bean, _optimizedIndex,
                _deserializeString(jp, ctxt));
    }

    @Override
    public void set(Object bean, Object value) throws IOException {
        _propertyMutator.stringSetter(_originalSettable, bean, _optimizedIndex, (String) value);
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser jp,
            DeserializationContext ctxt, Object instance)
        throws IOException, JsonProcessingException
    {
        return setAndReturn(instance, _deserializeString(jp, ctxt));
    }
}
