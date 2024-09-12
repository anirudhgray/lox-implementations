package com.tahini.lang;

import java.util.List;

abstract class Stmt {
  interface Visitor<R> {
    R visitExpressionStmt(Expression stmt);
    R visitFunctionStmt(Function stmt);
    R visitTestStmt(Test stmt);
    R visitPrintStmt(Print stmt);
    R visitIfStmt(If stmt);
    R visitVarStmt(Var stmt);
    R visitBlockStmt(Block stmt);
    R visitWhileStmt(While stmt);
    R visitBreakStmt(Break stmt);
    R visitReturnStmt(Return stmt);
    R visitContractStmt(Contract stmt);
  }
  static class Expression extends Stmt {
    Expression(Expr expression) {
      this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitExpressionStmt(this);
    }

    final Expr expression;
  }
  static class Function extends Stmt {
    Function(Token name, List<Token> params, List<Stmt> body, List<Expr> preconditions, List<Expr> postconditions) {
      this.name = name;
      this.params = params;
      this.body = body;
      this.preconditions = preconditions;
      this.postconditions = postconditions;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitFunctionStmt(this);
    }

    final Token name;
    final List<Token> params;
    final List<Stmt> body;
    final List<Expr> preconditions;
    final List<Expr> postconditions;
  }
  static class Test extends Stmt {
    Test(Token name, Stmt body) {
      this.name = name;
      this.body = body;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitTestStmt(this);
    }

    final Token name;
    final Stmt body;
  }
  static class Print extends Stmt {
    Print(Expr expression) {
      this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitPrintStmt(this);
    }

    final Expr expression;
  }
  static class If extends Stmt {
    If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
      this.condition = condition;
      this.thenBranch = thenBranch;
      this.elseBranch = elseBranch;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitIfStmt(this);
    }

    final Expr condition;
    final Stmt thenBranch;
    final Stmt elseBranch;
  }
  static class Var extends Stmt {
    Var(Token name, Expr initializer) {
      this.name = name;
      this.initializer = initializer;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitVarStmt(this);
    }

    final Token name;
    final Expr initializer;
  }
  static class Block extends Stmt {
    Block(List<Stmt> statements) {
      this.statements = statements;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBlockStmt(this);
    }

    final List<Stmt> statements;
  }
  static class While extends Stmt {
    While(Expr condition, Stmt body) {
      this.condition = condition;
      this.body = body;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitWhileStmt(this);
    }

    final Expr condition;
    final Stmt body;
  }
  static class Break extends Stmt {
    Break() {
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBreakStmt(this);
    }

  }
  static class Return extends Stmt {
    Return(Token keyword, Expr value) {
      this.keyword = keyword;
      this.value = value;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitReturnStmt(this);
    }

    final Token keyword;
    final Expr value;
  }
  static class Contract extends Stmt {
    Contract(Token type, List<Expr> conditions, Object msg) {
      this.type = type;
      this.conditions = conditions;
      this.msg = msg;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitContractStmt(this);
    }

    final Token type;
    final List<Expr> conditions;
    final Object msg;
  }

  abstract <R> R accept(Visitor<R> visitor);
}
