/* Generated By:JJTree: Do not edit this line. OBaseExpression.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.record.OIdentifiable;

import java.util.List;
import java.util.Map;

public class OBaseExpression extends OMathExpression {

  private static final Object UNSET           = new Object();
  private Object              inputFinalValue = UNSET;

  protected ONumber           number;

  protected OBaseIdentifier   identifier;

  protected OInputParameter   inputParam;

  protected String            string;

  OModifier                   modifier;

  public OBaseExpression(int id) {
    super(id);
  }

  public OBaseExpression(OrientSql p, int id) {
    super(p, id);
  }

  /** Accept the visitor. **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override
  public String toString() {
    return super.toString();
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    if (number != null) {
      number.toString(params, builder);
    } else if (identifier != null) {
      identifier.toString(params, builder);
    } else if (string != null) {
      builder.append(string);
    } else if (inputParam != null) {
      inputParam.toString(params, builder);
    }

    if (modifier != null) {
      modifier.toString(params, builder);
    }

  }

  public Object execute(OIdentifiable iCurrentRecord, OCommandContext ctx) {
    Object result = null;
    if (number != null) {
      result = number.getValue();
    }
    if (identifier != null) {
      result = identifier.execute(iCurrentRecord, ctx);
    }
    if (string != null && string.length() > 1) {
      result = string.substring(1, string.length() - 1);
    }
    if (modifier != null) {
      result = modifier.execute(iCurrentRecord, result, ctx);
    }
    return result;
  }

  @Override
  protected boolean supportsBasicCalculation() {
    return true;
  }

  @Override
  public boolean isIndexedFunctionCall() {
    if (this.identifier == null) {
      return false;
    }
    return identifier.isIndexedFunctionCall();
  }

  public long estimateIndexedFunction(OFromClause target, OCommandContext context, OBinaryCompareOperator operator, Object right) {
    if (this.identifier == null) {
      return -1;
    }
    return identifier.estimateIndexedFunction(target, context, operator, right);
  }

  public Iterable<OIdentifiable> executeIndexedFunction(OFromClause target, OCommandContext context,
      OBinaryCompareOperator operator, Object right) {
    if (this.identifier == null) {
      return null;
    }
    return identifier.executeIndexedFunction(target, context, operator, right);
  }

  @Override
  public boolean isBaseIdentifier() {
    return identifier != null && modifier == null && identifier.isBaseIdentifier();
  }

  public boolean isEarlyCalculated() {
    if (number != null || inputParam != null || string != null) {
      return true;
    }
    return false;
  }

  public boolean isAggregate() {
    if(super.isAggregate()){
      return true;
    }
    if(identifier!=null){
      return identifier.isAggregate();
    }
    return false;
  }

  public Object getAggregateResult(OCommandContext ctx) {
    if(identifier!=null){
      return identifier.getAggregateResult(ctx);
    }

    return null;
  }


  public boolean isFiltering() {
    if(super.isFiltering()){
      return true;
    }
    if(identifier!=null){
      return identifier.isFiltering();
    }
    return false;
  }

  public String getDefaultAlias() {
    if (number != null) {
      return number.toString();
    } else if (identifier != null) {
      return identifier.getDefaultAlias();
    } else if (string != null) {
      return string.substring(1, string.length() - 1);
    } else if (inputParam != null) {
      return inputParam.toString();
    }
    return "_col";
  }

  public Object mergeDistributedResult(List<Object> toMerge) {
    if (identifier != null) {
      return identifier.mergeDistributedResult(toMerge);
    }

      throw new IllegalStateException("Expression cannot be merged: "+toString());//TODO replace this with a proper exception!
  }


}
/* JavaCC - OriginalChecksum=71b3e2d1b65c923dc7cfe11f9f449d2b (do not edit this line) */
