package com.zac.spring.service;

import java.text.MessageFormat;

/**
 * メソッドチェーンにより、ログメッセージの出力を定型化します。
 */
public class LogMessageBuilder {

  /**
   * ログの最初に出力されるメッセージを入力してください。
   * 
   * @param pattern
   *          メッセージフォーマットのパターンでメッセージを入力します。
   * @param arguments
   *          メッセージフォーマットのパラメータ。
   * @return 次のメッセージオブジェクトを返します。
   */
  public static LogMessage message(String pattern, Object... arguments) {
    return new LogMessage(pattern, arguments);
  }

  public static class LogMessage {
    private StringBuilder sb = new StringBuilder();

    private LogMessage(String pattern, Object... arguments) {
      sb.append(MessageFormat.format(pattern, arguments)).append("\n");
    }

    /**
     * 問題発生の原因を記載してください。
     * 
     * @param cause
     *          問題発生の原因。
     * @return 次のメッセージオブジェクトを返します。
     */
    public Cause cause(String cause) {
      return new Cause(sb).cause(cause);
    }
  }

  public static class Cause {
    private StringBuilder sb;
    private int count;

    private Cause(StringBuilder sb) {
      this.sb = sb.append("[考えられる原因]\n");
      this.count = 0;
    }

    /**
     * 問題発生の原因を追加したい場合は記載してください。
     * 
     * @param cause
     *          問題発生の原因。
     * @return 次のメッセージオブジェクトを返します。
     */
    public Cause cause(String cause) {
      count++;
      sb.append(count + ". ");
      sb.append(cause).append("\n");
      return this;
    }

    /**
     * 問題発生によるユーザー影響を記載してください。
     * 
     * @param cause
     *          問題発生によるユーザー影響。
     * @return 次のメッセージオブジェクトを返します。
     */
    public Effect effect(String effect) {
      return new Effect(sb).effect(effect);
    }
  }

  public static class Effect {
    private StringBuilder sb;
    private int count;

    private Effect(StringBuilder sb) {
      this.sb = sb.append("[考えられるユーザー影響]\n");
      this.count = 0;
    }

    /**
     * 問題発生によるユーザー影響を追加したい場合は記載してください。
     * 
     * @param cause
     *          問題発生によるユーザー影響。
     * @return 次のメッセージオブジェクトを返します。
     */
    public Effect effect(String effect) {
      count++;
      sb.append(count + ". ");
      sb.append(effect).append("\n");
      return this;
    }

    /**
     * 取るべき対応を記載してください。
     * 
     * @param cause
     *          取るべき対応。
     * @return 次のメッセージオブジェクトを返します。
     */
    public Solution solution(String solution) {
      return new Solution(sb).solution(solution);
    }
  }

  public static class Solution {
    private StringBuilder sb;
    private int count;

    private Solution(StringBuilder sb) {
      this.sb = sb.append("[取るべき対応]\n");
      this.count = 0;
    }

    /**
     * 取るべき対応を追加したい場合は記載してください。
     * 
     * @param cause
     *          取るべき対応。
     * @return 次のメッセージオブジェクトを返します。
     */
    public Solution solution(String solution) {
      count++;
      sb.append(count + ". ");
      sb.append(solution).append("\n");
      return this;
    }

    /**
     * 作成したメッセージ文字列を返します。
     * 
     * @return 作成したメッセージ文字列。
     */
    public String build() {
      return sb.toString();
    }
  }
}